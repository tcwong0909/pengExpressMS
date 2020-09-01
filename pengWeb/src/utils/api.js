import axios from "axios"
import {Message} from "element-ui";
//请求拦截
axios.interceptors.request.use(config => {
  return config;
}, err => {
  Message.error({message: '请求超时!'});
})

//响应拦截
axios.interceptors.response.use(data => {
  if (data.status && data.status === 200 && data.data.code !== "0000") {
    //自定义异常
    Message.error({message: data.data.message});
  }
  return data.data;
}, err => {
    Message.error({message:err.response.data.message})
  return err.response;
});

let base = '';

export const postKeyValueRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
export const postRequest = (url, params) => {
  return axios({
    method: "post",
    url: `${base}${url}`,
    data: params,
    header: {
      'Content-Type': 'application/json'
    }
  });
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    header: {
      'Content-Type': 'application/json'
    }
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  })
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  })
}

