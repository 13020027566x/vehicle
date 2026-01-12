// 当前 host
const HOST = window.location.host

// 开发
const DEV = 'DEV'

// 生产
const PROD = 'PROD'

// ******************* 不使用Nginx反向代理时 ********************
// 当前环境
const ENV = HOST === 'localhost:9000' ? DEV : PROD
// 基础地址
const BASE_URL = ENV === DEV ? 'http://localhost:58080' : 'http://localhost:58080'
// ***********************************************************

// ******************* 使用Nginx反向代理时 *********************
// 当前环境
// const ENV = HOST === 'localhost' ? DEV : PROD
// 基础地址
// const BASE_URL = ENV === DEV ? 'http://localhost' : 'http://localhost'
// ***********************************************************

// ******************* 静态资源路径 ****************************
const STATIC_PATH = ENV === DEV ? '/static/' : '/static/'
// ***********************************************************

// 接口地址
const API_URL = BASE_URL + '/'

// 分页大小
const PAGE_SIZE = 10

// 网站标题
const TITLE = 'JEasy Admin系统'

const FORM_CONTENT_TYPE = 'application/x-www-form-urlencoded;charset=UTF-8'

const JSON_CONTENT_TYPE = 'application/json;charset=UTF-8'

export default {
  BASE_URL,
  API_URL,
  PAGE_SIZE,
  TITLE,
  STATIC_PATH,
  FORM_CONTENT_TYPE,
  JSON_CONTENT_TYPE
}
