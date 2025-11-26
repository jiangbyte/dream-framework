<script lang="ts" setup>
import { useAuthPasswordResetApi } from '@/api'
import { useBoolean, useLoading } from '@/hooks'
import { ResetFormData } from '@/utils'

const props = defineProps<{
  formName?: string
}>()

const { value: visible, setFalse: closeDrawer, setTrue: openDrawer } = useBoolean(false)
const { isLoading, withLoading } = useLoading()

const formData = reactive<DataFormType>({})

function doClose() {
  ResetFormData(formData)
  closeDrawer()
}

async function doOpen(row: any) {
  openDrawer()
  ResetFormData(formData)

  if (row?.id) {
    const { data, success } = await withLoading(useAuthPasswordResetApi().GetAuthPasswordReset(row?.id))
    if (success) {
      Object.assign(formData, data)
    }
    else {
      closeDrawer()
    }
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
    :confirm-btn="null"
    size="large"
    destroy-on-close
    @close="doClose"
  >
    <template #header>
      {{ `${props.formName}详情` }}
    </template>
    <t-loading size="small" :loading="isLoading" show-overlay class="w-full">
      <t-descriptions :column="1" colon table-layout="auto">
        <t-descriptions-item label="账户ID">
          {{ formData.accountId }}
        </t-descriptions-item>
        <t-descriptions-item label="重置令牌">
          {{ formData.token }}
        </t-descriptions-item>
        <t-descriptions-item label="接收重置邮件的邮箱">
          {{ formData.email }}
        </t-descriptions-item>
        <t-descriptions-item label="令牌过期时间">
          {{ formData.expiresAt }}
        </t-descriptions-item>
        <t-descriptions-item label="是否已使用">
          {{ formData.used }}
        </t-descriptions-item>
      </t-descriptions>
    </t-loading>
  </t-drawer>
</template>

<style scoped>

</style>
