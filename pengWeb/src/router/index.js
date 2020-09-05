import Vue from 'vue'
import Router from 'vue-router'
// import Login from '@/components/Login'
// import Home from '@/components/Home'
// import Truck from '@/components/truck/Truck'
// import TruckTeam from '@/components/truck/TruckTeam'
// import BindTruck from '@/components/truck/BindTruck'
// import Driver from '@/components/driver/Driver'
// import Capacity from '@/components/capacity/Capacity'
// import CapacityHistory from '@/components/capacity/CapacityHistory'
// import CostAccounting from '@/components/cost/CostAccounting'
// import CostMaintenance from '@/components/cost/CostMaintenance'
// import Schedule from '@/components/schedule/Schedule'
// import User from '@/components/system/User'
// import LogDic from '@/components/system/LogDic'
// import SysLog from '@/components/system/SysLog'
// import BillCreate from '@/components/waybill/BillCreate'
// import BillReceive from '@/components/waybill/BillReceive'
// import LoginLog from '@/components/system/LoginLog'


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      label: '登陆',
      name:'login',
      hidden:true,
      component: ()=>import('@/components/Login'),
    },
    {
      path:'/home',
      label:'首页',
      name:'home',
      hidden:true,
      component: ()=>import('@/components/Home'),
    },
    {
      path:'/home',
      label:'车辆管理',
      component: ()=>import('@/components/Home'),
      icon:'el-icon-truck',
      meta:
        {
          rolesId:[2,9],
          rolesName:['运输管理员']
        },
      children:[
        {
          path:'/truck',
          label:'车辆信息维护',
          component: ()=>import('@/components/truck/Truck'),
        },
        {
          path:'/bindTruck',
          label:'绑定车辆信息',
          component: ()=>import('@/components/truck/BindTruck'),
        },
        {
          path:'/truckTeam',
          label:'车队信息维护',
          component: ()=>import('@/components/truck/TruckTeam'),
        }
      ]
    },
    {
      path:'/home',
      label:'驾驶员管理',
      component: ()=>import('@/components/Home'),
      icon:'el-icon-user',
      meta:
        {
          rolesId:[2,9],
          rolesName:['运输管理员']
        },
      children:[
        {
          path:'/driver',
          label:'驾驶员信息维护',
          component: ()=>import('@/components/driver/Driver'),
        }
      ]
    }, {
      path:'/home',
      label:'运力查询',
      component: ()=>import('@/components/Home'),
      icon: 'el-icon-wind-power',
      meta:
        {
          rolesId:[5,3,9],
          rolesName:['调度员','财务人员']
        },
      children:[
        {
          path:'/capacity',
          label:'运力综合查询',
          component: ()=>import('@/components/capacity/Capacity'),
        },{
          path:'/capacityHistory',
          label:'历史承运任务查询',
          component: ()=>import('@/components/capacity/CapacityHistory'),
        }
      ]
    }, {
      path:'/home',
      label:'运输成本核算',
      component: ()=>import('@/components/Home'),
      meta:
        {
          rolesId:[3,9],
          rolesName:['财务人员']
        },
      icon: 'el-icon-circle-check',
      children:[
        {
          path:'/costMaintenance',
          label:'车队运输成本维护',
          component: ()=>import('@/components/cost/CostMaintenance'),
        },
        {
          path:'/costAccounting',
          label:'车队运输成本核算',
          component: ()=>import('@/components/cost/CostAccounting'),
        }
      ]
    }, {
      path:'/home',
      label:'调度任务',
      component: ()=>import('@/components/Home'),
      meta:
        {
          rolesId:[5,9],
          rolesName:['调度员']
        },
      icon:'el-icon-office-building',
      children:[
        {
          path:'/schedule',
          label:'调度承运任务',
          component: ()=>import('@/components/schedule/Schedule'),
        }
      ]
    }, {
      path:'/home',
      label:'承运任务管理',
      component:()=>import('@/components/Home'),
      meta:
        {
          rolesId:[4,9],
          rolesName:['承运业务员']
        },
      icon: 'el-icon-document',
      children:[
        {
          path:'/billCreate',
          label:'承运单开出',
          component: ()=>import('@/components/waybill/BillCreate'),
        },
        {
          path:'/billReceive',
          label:'承运单接收',
          component: ()=>import('@/components/waybill/BillReceive'),
        }
      ]
    }, {
      path:'/home',
      label:'系统维护',
      component:()=>import('@/components/Home'),
      icon:'el-icon-s-custom',
      meta:
        {
          rolesId:[1,9],
          rolesName:['系统管理员']
        },
      children:[
        {
          path:'/user',
          label:'用户维护',
          component: ()=>import('@/components/system/User'),
        },
        {
          path:'/logDic',
          label:'日志字典',
          component: ()=>import('@/components/system/LogDic'),
        },
        {
          path:'/sysLog',
          label:'系统日志',
          component: ()=>import('@/components/system/SysLog'),
        },
        {
          path:'/loginLog',
          label:'登陆日志',
          component: ()=>import('@/components/system/LoginLog'),
        }
      ]
    },

  ]
})
