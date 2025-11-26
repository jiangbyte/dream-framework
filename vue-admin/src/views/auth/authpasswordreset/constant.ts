import type { DropdownOption, PrimaryTableCol } from 'tdesign-vue-next'

export const COLUMNS: PrimaryTableCol[] = [
  {
    colKey: 'row-select',
    title: '选择',
    type: 'multiple',
    width: 40,
    fixed: 'left',
  },
  {
    title: '账户ID',
    colKey: 'accountId',
    width: 120,
    ellipsis: true,
  },
  {
    title: '重置令牌',
    colKey: 'token',
    width: 120,
    ellipsis: true,
  },
  {
    title: '接收重置邮件的邮箱',
    colKey: 'email',
    width: 120,
    ellipsis: true,
  },
  {
    title: '令牌过期时间',
    colKey: 'expiresAt',
    width: 120,
    ellipsis: true,
  },
  {
    title: '是否已使用',
    colKey: 'used',
    width: 120,
    ellipsis: true,
  },
  {
    title: '创建时间',
    colKey: 'createdAt',
    width: 120,
    ellipsis: true,
  },
  {
    title: '更新时间',
    colKey: 'updatedAt',
    width: 120,
    ellipsis: true,
  },
  {
    title: '操作',
    colKey: 'operation',
    width: 180,
    align: 'center',
    fixed: 'right',
  },
]

export const SortOptions: DropdownOption[] = [
  {
    value: 'id',
    label: 'ID',
  },
  ...COLUMNS
    .filter(column =>
      column.colKey !== 'row-select'
      && column.colKey !== 'operation'
      && column.title,
    )
    .map(column => ({
      value: column.colKey,
      label: column.title as string,
    })),
]
