import { request } from '@/utils'

export function useAccessApi() {
  const pathPrefix = `/api/v1`
  return {
    Captcha() {
      return request.Get<IResult<any>>(`${pathPrefix}/access/captcha`)
    },
    DoLogin(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/access/login`, data)
    },
    DoLogout() {
      return request.Post<IResult<any>>(`${pathPrefix}/access/logout`)
    },
    DoRegister(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/access/register`, data)
    },
    // 密码相关
    DoResetPassword(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/access/password/reset`, data)
    },
    DoValidateResetPasswordToken(data: any) {
      return request.Get<IResult<any>>(`${pathPrefix}/access/password/reset/validate`, {
        params: {
          token: data,
        },
      })
    },
    DoPasswordResetConfirm(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/password/reset/confirm`, data)
    },
    ChangePassword(data: any) {
      return request.Post<IResult<any>>(`${pathPrefix}/password/change`, data)
    },
  }
}
