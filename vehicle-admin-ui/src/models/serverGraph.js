import REST from '@/utils/rest'
import restHelpers from '@/utils/helpers/restHelpers'

export default class extends REST {
  constructor () {
    super()
    this.baseURL = 'http://hack.fenbeijinfu.com/platform'
    this.errorHandler = restHelpers.errorHandler
    this.path = 'finhub-resource/-/blob/'
  }
}
