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
      name: '登陆',
      hidden:true,
      component: ()=>import('@/components/Login'),
    },
    {
      path:'/home',
      name:'首页',
      hidden:true,
      component: ()=>import('@/components/Home'),
    },
    {
      path:'/home',
      name:'车辆管理',
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
          name:'车辆信息维护',
          component: ()=>import('@/components/truck/Truck'),
        },
        {
          path:'/bindTruck',
          name:'绑定车辆信息',
          component: ()=>import('@/components/truck/BindTruck'),
        },
        {
          path:'/truckTeam',
          name:'车队信息维护',
          component: ()=>import('@/components/truck/TruckTeam'),
        }
      ]
    },
    {
      path:'/home',
      name:'驾驶员管理',
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
          name:'驾驶员信息维护',
          component: ()=>import('@/components/driver/Driver'),
        }
      ]
    }, {
      path:'/home',
      name:'运力查询',
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
          name:'运力综合查询',
          component: ()=>import('@/components/capacity/Capacity'),
        },{
          path:'/capacityHistory',
          name:'历史承运任务查询',
          component: ()=>import('@/components/capacity/CapacityHistory'),
        }
      ]
    }, {
      path:'/home',
      name:'运输成本核算',
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
          name:'车队运输成本维护',
          component: ()=>import('@/components/cost/CostMaintenance'),
        },
        {
          path:'/costAccounting',
          name:'车队运输成本核算',
          component: ()=>import('@/components/cost/CostAccounting'),
        }
      ]
    }, {
      path:'/home',
      name:'调度任务',
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
          name:'调度承运任务',
          component: ()=>import('@/components/schedule/Schedule'),
        }
      ]
    }, {
      path:'/home',
      name:'承运任务管理',
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
          name:'承运单开出',
          component: ()=>import('@/components/waybill/BillCreate'),
        },
        {
          path:'/billReceive',
          name:'承运单接收',
          component: ()=>import('@/components/waybill/BillReceive'),
        }
      ]
    }, {
      path:'/home',
      name:'系统维护',
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
          name:'用户维护',
          component: ()=>import('@/components/system/User'),
        },
        {
          path:'/logDic',
          name:'日志字典',
          component: ()=>import('@/components/system/LogDic'),
        },
        {
          path:'/sysLog',
          name:'系统日志',
          component: ()=>import('@/components/system/SysLog'),
        },
        {
          path:'/loginLog',
          name:'登陆日志',
          component: ()=>import('@/components/system/LoginLog'),
        }
      ]
    },

  ]
})
