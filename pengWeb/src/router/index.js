import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: '登陆',
      title:'login',
      hidden:true,
      component: ()=>import('@/components/Login'),
    },
    {
      path:'/home',
      name:'首页',
      title:'home',
      hidden:false,
      icon:'el-icon-s-home',
      component: ()=>import('@/components/Home'),
    },
    {
      path:'/home',
      name:'车辆管理',
      title:'truckManager',
      component: ()=>import('@/components/Home'),
      icon:'el-icon-truck',
      children:[
        {
          path:'/truck',
          title:'truckMessage',
          name:'车辆信息维护',
          component: ()=>import('@/components/truck/Truck'),
        },
        {
          path:'/bindTruck',
          title:'truckBind',
          name:'绑定车辆信息',
          component: ()=>import('@/components/truck/BindTruck'),
        },
        {
          path:'/truckTeam',
          title:'truckTeamMessage',
          name:'车队信息维护',
          component: ()=>import('@/components/truck/TruckTeam'),
        }
      ]
    },
    {
      path:'/home',
      title:'driverManager',
      name:'驾驶员管理',
      component: ()=>import('@/components/Home'),
      icon:'el-icon-user',
      children:[
        {
          path:'/driver',
          title:'driverMessage',
          name:'驾驶员信息维护',
          component: ()=>import('@/components/driver/Driver'),
        }
      ]
    }, {
      path:'/home',
      name:'运力查询',
      title:'capacityManager',
      component: ()=>import('@/components/Home'),
      icon: 'el-icon-wind-power',
      children:[
        {
          path:'/capacity',
          title:'capacityMessage',
          name:'运力综合查询',
          component: ()=>import('@/components/capacity/Capacity'),
        },{
          path:'/capacityHistory',
          title:'capacityHistory',
          name:'历史承运任务查询',
          component: ()=>import('@/components/capacity/CapacityHistory'),
        }
      ]
    }, {
      path:'/home',
      title:'costManager',
      name:'运输成本核算',
      component: ()=>import('@/components/Home'),
      icon: 'el-icon-circle-check',
      children:[
        {
          path:'/costMaintenance',
          title:'costMaintenance',
          name:'车队运输成本维护',
          component: ()=>import('@/components/cost/CostMaintenance'),
        },
        {
          path:'/costAccounting',
          title:'costAccounting',
          name:'车队运输成本核算',
          component: ()=>import('@/components/cost/CostAccounting'),
        }
      ]
    }, {
      path:'/home',
      title:'scheduleManager',
      name:'调度任务',
      component: ()=>import('@/components/Home'),
      icon:'el-icon-office-building',
      children:[
        {
          path:'/schedule',
          title:'scheduleMessage',
          name:'调度承运任务',
          component: ()=>import('@/components/schedule/Schedule'),
        }
      ]
    }, {
      path:'/home',
      title:'billManager',
      name:'承运任务管理',
      component:()=>import('@/components/Home'),
      icon: 'el-icon-document',
      children:[
        {
          path:'/billCreate',
          title:'billCreate',
          name:'承运单开出',
          component: ()=>import('@/components/waybill/BillCreate'),
        },
        {
          path:'/billReceive',
          title:'billReceive',
          name:'承运单接收',
          component: ()=>import('@/components/waybill/BillReceive'),
        }
      ]
    }, {
      path:'/home',
      title:'systemManager',
      name:'系统维护',
      component:()=>import('@/components/Home'),
      icon:'el-icon-s-custom',
      children:[
        {
          path:'/user',
          title:'userMessage',
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
          title:'systemLogMessage',
          name:'系统日志',
          component: ()=>import('@/components/system/SysLog'),
        },
        {
          path:'/loginLog',
          title:'loginLogMessage',
          name:'登陆日志',
          component: ()=>import('@/components/system/LoginLog'),
        }
      ]
    },
  ]
})
