<script lang="ts" setup>
import type { FormInst, FormItemRule } from 'naive-ui'
import { useAppStore } from '@/stores'
import { useAccessApi } from '@/api'

const route = useRoute()
const router = useRouter()
const token = ref('')

// 从URL参数获取token
onMounted(() => {
  const tokenParam = route.query.token as string
  if (tokenParam) {
    token.value = tokenParam

    useAccessApi().DoValidateResetPasswordToken(token.value).then(({ success }) => {
      if (!success) {
        window.$message.error('无效的重置链接')
        router.push('/login')
      }
    })
  }
  else {
    window.$message.error('无效的重置链接')
    router.push('/login')
  }
})

const formRef = ref<FormInst | null>(null)
const formData = ref({
  password: '',
  confirmPassword: '',
})

const formRules = {
  password: {
    required: true,
    validator(rule: FormItemRule, value: string) {
      if (!value) {
        return new Error('请输入新密码')
      }
      // 长度限制：6-20个字符
      if (value.length < 6) {
        return new Error('密码长度不能少于6个字符')
      }
      if (value.length > 20) {
        return new Error('密码长度不能超过20个字符')
      }
      return true
    },
    trigger: ['input', 'blur'],
  },
  confirmPassword: {
    required: true,
    validator(rule: FormItemRule, value: string) {
      if (!value) {
        return new Error('请确认密码')
      }
      if (value !== formData.value.password) {
        return new Error('两次输入的密码不一致')
      }
      return true
    },
    trigger: ['input', 'blur'],
  },
}

const resetSuccess = ref(false)
const countdown = ref(5)
const countdownTimer = ref<NodeJS.Timeout | null>(null)

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
    if (!errors && token.value) {
      isLoading.value = true
      useAccessApi().DoPasswordResetConfirm({
        token: token.value,
        password: formData.value.password,
      }).then(({ success }) => {
        isLoading.value = false
        if (success) {
          resetSuccess.value = true
          window.$message.success('密码重置成功！')
          startCountdown()
        }
        else {
          window.$message.error('密码重置失败，请重试')
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
        title="密码重置成功"
      >
        <NResult
          status="success"
          title="密码重置成功"
          description="您的密码已成功重置，请使用新密码登录"
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
            重置密码
          </n-text>
          <NText
            depth="3"
            class="text-center text-3"
          >
            {{ websiteConfig?.website_name }} {{ websiteConfig?.website_version }}
          </NText>
        </NFlex>

        <NForm
          v-if="!resetSuccess && token"
          ref="formRef"
          :label-width="100"
          :model="formData"
          :rules="formRules"
          label-placement="left"
          @keypress.enter="handleResetPassword"
        >
          <NFormItem
            label="新密码"
            path="password"
          >
            <NInput
              v-model:value="formData.password"
              type="password"
              data-testid="password-inp"
              :input-props="{
                autocomplete: 'new-password',
              }"
              placeholder="请输入新密码"
              show-password-toggle
            />
          </NFormItem>
          <NFormItem
            label="确认密码"
            path="confirmPassword"
          >
            <NInput
              v-model:value="formData.confirmPassword"
              type="password"
              data-testid="confirm-password-inp"
              :input-props="{
                autocomplete: 'new-password',
              }"
              placeholder="请再次输入新密码"
              show-password-toggle
            />
          </NFormItem>
        </NForm>

        <div v-else-if="!token" class="text-center">
          <NResult
            status="error"
            title="无效的重置链接"
            description="请检查您的重置链接是否正确，或重新申请密码重置"
          >
            <template #footer>
              <NButton type="primary" @click="$router.push('/forget')">
                重新申请重置
              </NButton>
            </template>
          </NResult>
        </div>

        <n-flex
          v-if="!resetSuccess && token"
          align="center"
        >
          <NButton
            block
            type="primary"
            :loading="isLoading"
            data-testid="reset-execute"
            @click="handleResetPassword"
          >
            {{ isLoading ? '重置中...' : '重置密码' }}
          </NButton>
        </n-flex>

        <n-flex
          v-if="!resetSuccess && token"
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
