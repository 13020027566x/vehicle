function refreshMonitor () {

  $.ajax({
    url: 'http://localhost:58080/monitor/report?type=controller',
    type: 'get',
    dataType: 'json',
    success: function (res) {
      $('#controllerMonitor').datagrid({
        singleSelect: true,
        collapsible: true,
        data: res.data.item
      })
    },
    error: function () {}
  })

  $.ajax({
    url: 'http://localhost:58080/monitor/report?type=service',
    type: 'get',
    dataType: 'json',
    success: function (res) {
      $('#serviceMonitor').datagrid({
        singleSelect: true,
        collapsible: true,
        data: res.data.item
      })
    },
    error: function () {}
  })

  $.ajax({
    url: 'http://localhost:58080/monitor/report?type=dao',
    type: 'get',
    dataType: 'json',
    success: function (res) {
      $('#daoMonitor').datagrid({
        singleSelect: true,
        collapsible: true,
        data: res.data.item
      })
    },
    error: function () {}
  })
}

$(function () {
  $.ajaxSetup({
    xhrFields: {
      withCredentials: true
    },
    crossDomain: true
  })

  refreshMonitor()
  var int = self.setInterval('refreshMonitor()', 10000) // 开启定时
  // int=window.clearInterval(int); 关闭定时
})
