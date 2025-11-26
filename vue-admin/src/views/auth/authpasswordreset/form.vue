<script lang="ts" setup>
import { useAuthPasswordResetApi } from '@/api'
import { useBoolean, useLoading } from '@/hooks'
import { ResetFormData } from '@/utils'

const props = defineProps<{
  formName?: string
}>()

const emit = defineEmits(['close', 'submit'])

const { value: visible, setFalse: closeDrawer, setTrue: openDrawer } = useBoolean(false)
const { value: isEdit, setFalse: setAddMode, setTrue: setEditMode } = useBoolean(false)
const { isLoading, withLoading } = useLoading()

const formData = reactive<DataFormType>({})

async function doOpen(row: any) {
  openDrawer()
  ResetFormData(formData)

  if (row?.id) {
    setEditMode()
    const { data, success } = await withLoading(useAuthPasswordResetApi().GetAuthPasswordReset(row?.id))
    if (success) {
      Object.assign(formData, data)
    }
    else {
      closeDrawer()
    }
  }
  else {
    setAddMode()
  }
}

function doClose() {
  ResetFormData(formData)
  closeDrawer()
  emit('close')
}

async function doSubmit() {
  const api = isEdit.value
    ? useAuthPasswordResetApi().EditAuthPasswordReset
    : useAuthPasswordResetApi().AddAuthPasswordReset

  const { success } = await withLoading(api(formData))
  if (success) {
    closeDrawer()
    emit('submit')
  }
}

defineExpose({
  doOpen,
})
</script>

<template>
  <t-drawer
    v-model:visible="visible"
    :close-btn="true"
    :close-on-overlay-click="false"
    :confirm-btn="{ disabled: isLoading }"
    size="large"
    destroy-on-close
    @close="doClose"
    @confirm="doSubmit"
  >
    <template #header>
      {{ isEdit ? `编辑${props.formName}` : `新增${props.formName}` }}
    </template>
    <t-loading size="small" :loading="isLoading" show-overlay class="w-full">
      <t-form :data="formData" label-align="left">
        <t-form-item label="账户ID" name="accountId">
          <t-input v-model="formData.accountId" placeholder="请输入账户ID" />
        </t-form-item>
        <t-form-item label="重置令牌" name="token">
          <t-input v-model="formData.token" placeholder="请输入重置令牌" />
        </t-form-item>
        <t-form-item label="接收重置邮件的邮箱" name="email">
          <t-input v-model="formData.email" placeholder="请输入接收重置邮件的邮箱" />
        </t-form-item>
        <t-form-item label="令牌过期时间" name="expiresAt">
          <t-input v-model="formData.expiresAt" placeholder="请输入令牌过期时间" />
        </t-form-item>
        <t-form-item label="是否已使用" name="used">
          <t-input v-model="formData.used" placeholder="请输入是否已使用" />
        </t-form-item>
      </t-form>
    </t-loading>
  </t-drawer>
</template>

<style scoped>

</style>
