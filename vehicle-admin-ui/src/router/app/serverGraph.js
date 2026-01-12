export default [
  {
    path: '/serverGraph',
    name: 'serverGraph',
    meta: {
      icon: 'ios-document',
      title: '服务拓扑'
    },
    component: () => import('@/app/serverGraph')
  }
]
