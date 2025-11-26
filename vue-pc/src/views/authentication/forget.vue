<script lang="ts" setup>
import type { FormInst, FormItemRule } from 'naive-ui'
import { useAppStore } from '@/stores'
import { useAccessApi } from '@/api'

const formRef = ref<FormInst | null>(null)
const formData = ref({
  email: '',
})

const formRules = {
  email: {
    required: true,
    validator(rule: FormItemRule, value: string) {
      if (!value)
        return new Error('请输入邮箱地址')

      // 更严格的邮箱正则表达式
      const emailRegex = /^[\w.%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/i

      if (!emailRegex.test(value)) {
        return new Error('邮箱格式不正确')
      }

      // 检查邮箱长度
      if (value.length > 254) {
        return new Error('邮箱地址过长')
      }

      // 检查本地部分（@之前的部分）长度
      const localPart = value.split('@')[0]
      if (localPart.length > 64) {
        return new Error('邮箱用户名部分过长')
      }

      return true
    },
    trigger: ['input', 'blur'],
  },
}

const resetSuccess = ref(false)
const countdown = ref(5)
const countdownTimer = ref<NodeJS.Timeout | null>(null)

const router = useRouter()

// 倒计时跳转函数
function startCountdown() {
  countdownTimer.value = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) {
      if (countdownTimer.value) {
        clearInterval(countdownTimer.value)
      }
      router.push('/login')
    }
  }, 1000)
}

// 清理定时器
onUnmounted(() => {
  if (countdownTimer.value) {
    clearInterval(countdownTimer.value)
  }
})

const isLoading = ref(false)

async function handleResetPassword(e: MouseEvent) {
  e.preventDefault()
  formRef.value?.validate(async (errors) => {
    if (!errors) {
      isLoading.value = true
      useAccessApi().DoResetPassword(formData.value).then(({ success }) => {
        isLoading.value = false
        if (success) {
          resetSuccess.value = true
          window.$message.success('重置邮件发送成功！')
          startCountdown()
        }
        else {
          window.$message.error('重置邮件发送失败，请稍后再试')
        }
      })
    }
  })
}

const appStore = useAppStore()
const { websiteConfig } = storeToRefs(appStore)
</script>

<template>
  <n-flex align="center" justify="center" class="w-screen h-screen">
    <NCard
      class="w-90"
    >
      <NModal
        v-model:show="resetSuccess"
        :mask-closable="false"
        preset="dialog"
        title="重置邮件已发送"
      >
        <NResult
          status="success"
          title="重置邮件发送成功"
          description="请查看您的邮箱，按照邮件中的指示重置密码"
        >
          <template #footer>
            <NSpace vertical>
              <NText depth="3">
                {{ countdown }} 秒后自动跳转到登录页面
              </NText>
              <NButton
                type="primary"
                @click="$router.push('/login')"
              >
                立即跳转 ({{ countdown }}s)
              </NButton>
            </NSpace>
          </template>
        </NResult>
      </NModal>

      <n-space vertical>
        <NFlex vertical>
          <n-text class="text-center text-xl">
            忘记密码
          </n-text>
          <NText
            depth="3"
            class="text-center text-3"
          >
            {{ websiteConfig?.website_name }} {{ websiteConfig?.website_version }}
          </NText>
        </NFlex>

        <NForm
          v-if="!resetSuccess"
          ref="formRef"
          :label-width="75"
          :model="formData"
          :rules="formRules"
          label-placement="left"
          @keypress.enter="handleResetPassword"
        >
          <NFormItem
            label="邮箱"
            path="email"
          >
            <NInput
              v-model:value="formData.email"
              data-testid="email-inp"
              :input-props="{
                autocomplete: 'email',
              }"
              placeholder="请输入注册时使用的邮箱"
            />
          </NFormItem>
        </NForm>

        <n-flex
          v-if="!resetSuccess"
          align="center"
        >
          <NButton
            block
            type="primary"
            :loading="isLoading"
            data-testid="reset-execute"
            @click="handleResetPassword"
          >
            {{ isLoading ? '发送中...' : '发送重置邮件' }}
          </NButton>
        </n-flex>

        <n-flex
          v-if="!resetSuccess"
          align="center"
          justify="space-between"
        >
          <NText depth="3">
            想起密码了？
          </NText>
          <NButton
            text
            type="primary"
            @click="$router.push('/login')"
          >
            返回登录
          </NButton>
        </n-flex>
      </n-space>

      <template #footer>
        <n-flex align="center" justify="center">
          <NText
            class="text-3"
            depth="3"
          >
            {{ websiteConfig?.copyright }}
          </NText>
        </n-flex>
      </template>
    </NCard>
  </n-flex>
</template>

<style scoped>
</style>
