import request from '@/utils/request'

// 查询房型列表
export function listRoomType(query) {
  return request({
    url: '/com/RoomType/list',
    method: 'get',
    params: query
  })
}

// 查询房型详细
export function getRoomType(id) {
  return request({
    url: '/com/RoomType/' + id,
    method: 'get'
  })
}

// 新增房型
export function addRoomType(data) {
  return request({
    url: '/com/RoomType',
    method: 'post',
    data: data
  })
}

// 修改房型
export function updateRoomType(data) {
  return request({
    url: '/com/RoomType',
    method: 'put',
    data: data
  })
}

// 删除房型
export function delRoomType(id) {
  return request({
    url: '/com/RoomType/' + id,
    method: 'delete'
  })
}
