import { request } from '@/utils'

/**
 * 用户基本信息 API请求
 */
export function useUserInfoApi() {
  const pathPrefix = '/api/v1'

  return {
    /*
     * 用户基本信息 分页接口
     */
    PageUserInfo(data: any) {
      return request.Get<IResult<any>>(`${pathPrefix}/user/info/page`, {
        params: {
          ...data,
        },
      })
    },

    /*
     * 用户基本信息 新增接口
     */
    AddUserInfo(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/user/info/add`, data)
    },

    /*
     * 用户基本信息 修改接口
     */
    EditUserInfo(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/user/info/edit`, data)
    },

    /*
     * 用户基本信息 删除接口
     */
    DeleteUserInfo(ids: string[]) {
      return request.Post<IResult<any>>(`${pathPrefix}/user/info/delete`, ids)
    },

    /*
     * 用户基本信息 详情接口
     */
    GetUserInfo(id: string) {
      return request.Get<IResult<any>>(`${pathPrefix}/user/info/detail/${id}`)
    },

    /*
    * 用户基本信息 最新接口
    */
    LatestUserInfo(n: number) {
      return request.Get<IResult<any>>(`${pathPrefix}/user/info/latest`, {
        params: {
          n,
        },
      })
    },

    /*
    * 用户基本信息 TopN接口
    */
    TopUserInfo(n: number) {
      return request.Get<IResult<any>>(`${pathPrefix}/user/info/top`, {
        params: {
          n,
        },
      })
    },
  }
}
