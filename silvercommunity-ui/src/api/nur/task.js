import request from '@/utils/request'

// 查询护理任务列表
export function listTask(query) {
  return request({
    url: '/nur/task/list',
    method: 'get',
    params: query
  })
}

// 查询护理任务详细
export function getTask(id) {
  return request({
    url: '/nur/task/' + id,
    method: 'get'
  })
}

// 新增护理任务
export function addTask(data) {
  return request({
    url: '/nur/task',
    method: 'post',
    data: data
  })
}

// 修改护理任务
export function updateTask(data) {
  return request({
    url: '/nur/task',
    method: 'put',
    data: data
  })
}

// 删除护理任务
export function delTask(id) {
  return request({
    url: '/nur/task/' + id,
    method: 'delete'
  })
}
