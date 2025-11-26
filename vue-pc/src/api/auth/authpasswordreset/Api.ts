import { request } from '@/utils'

/**
 * 密码重置 API请求
 */
export function useAuthPasswordResetApi() {
  const pathPrefix = '/api/v1'

  return {
    /*
     * 密码重置 分页接口
     */
    PageAuthPasswordReset(data: any) {
      return request.Get<IResult<any>>(`${pathPrefix}/auth/password/reset/page`, {
        params: {
          ...data,
        },
      })
    },

    /*
     * 密码重置 新增接口
     */
    AddAuthPasswordReset(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/auth/password/reset/add`, data)
    },

    /*
     * 密码重置 修改接口
     */
    EditAuthPasswordReset(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/auth/password/reset/edit`, data)
    },

    /*
     * 密码重置 删除接口
     */
    DeleteAuthPasswordReset(ids: string[]) {
      return request.Post<IResult<any>>(`${pathPrefix}/auth/password/reset/delete`, ids)
    },

    /*
     * 密码重置 详情接口
     */
    GetAuthPasswordReset(id: string) {
      return request.Get<IResult<any>>(`${pathPrefix}/auth/password/reset/detail/${id}`)
    },

    /*
    * 密码重置 最新接口
    */
    LatestAuthPasswordReset(n: number) {
      return request.Get<IResult<any>>(`${pathPrefix}/auth/password/reset/latest`, {
        params: {
          n,
        },
      })
    },

    /*
    * 密码重置 TopN接口
    */
    TopAuthPasswordReset(n: number) {
      return request.Get<IResult<any>>(`${pathPrefix}/auth/password/reset/top`, {
        params: {
          n,
        },
      })
    },
  }
}
