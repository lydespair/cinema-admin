import request from '@/utils/request'

// 查询用户管理列表
export function listManage(query) {
  return request({
    url: '/users/manage/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getManage(userId) {
  return request({
    url: '/users/manage/' + userId,
    method: 'get'
  })
}

// 新增用户管理
export function addManage(data) {
  return request({
    url: '/users/manage',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updateManage(data) {
  return request({
    url: '/users/manage',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delManage(userId) {
  return request({
    url: '/users/manage/' + userId,
    method: 'delete'
  })
}
