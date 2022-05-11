import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: '首页',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '美发屋后台管理系统', icon: 'dashboard' }
    }]
  },
  {
    path: '/orders',
    component: Layout,
    //redirect: '/example/table',
    meta: {
  		roles:['admin']
  	},
    children: [
      {
        path: 'index',
        name: 'orders',
        component: () => import('@/views/orders/index'),
        meta: { title: '订单管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/menu',
    component: Layout,
    redirect: '/example/table',
    meta: {
  		roles:['admin']
  	},
    children: [
      {
        path: 'index',
        name: 'menu',
        component: () => import('@/views/menu/index'),
        meta: { title: '点餐管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/timeslotcount',
    component: Layout,
    redirect: '/example/table',
    meta: {
  		roles:['admin']
  	},
    children: [
      {
        path: 'index',
        name: 'timeslotcount',
        component: () => import('@/views/timeslotcount/index'),
        meta: { title: '各时段预约人数', icon: 'user' }
      }
    ]
  },
  {
    path: '/barbers_state',
    component: Layout,
    redirect: '/example/table',
    meta: {
  		roles:['admin']
  	},
    children: [
      {
        path: 'index',
        name: 'barbers_state',
        component: () => import('@/views/barbers_state/index'),
        meta: { title: '理发师状态', icon: 'eye-open' }
      }
    ]
  },
	{
	  path: '/peopleManagement',
	  component: Layout,
	  redirect: '/peopleManagement/users',
	  meta: {
			title: '人员管理',
			icon: 'user'
		},
	  children: [
	    {
	      path: 'users',
	      name: 'users',
	      component: () => import('@/views/peopleManagement/users/index'),
	      meta: { title: '用户管理', icon: 'user' }
	    },
      {
        path: 'barbers',
        name: 'barbers',
        component: () => import('@/views/peopleManagement/barbers/index'),
        meta: { title: '理发师管理', icon: 'user' }
      },
      {
        path: 'waiters',
        name: 'waiters',
        component: () => import('@/views/peopleManagement/waiters/index'),
        meta: { title: '服务人员管理', icon: 'user' }
      },
	  ]
	},
  {
    path: '/ActivitiesSales',
    component: Layout,
    redirect: '/ActivitiesSales/pricelist',
    meta: {
  		title: '活动及销售情况',
  		icon: 'nested'
  	},
    children: [
      {
        path: 'pricelist',
        name: 'pricelist',
        component: () => import('@/views/ActivitiesSales/pricelist/index'),
        meta: { title: '价目表', icon: 'nested' }
      },
      {
        path: 'publicity',
        name: 'publicity',
        component: () => import('@/views/ActivitiesSales/publicity/index'),
        meta: { title: '优惠活动', icon: 'nested' }
      },
      {
        path: 'RechargeRecord',
        name: 'RechargeRecord',
        component: () => import('@/views/ActivitiesSales/RechargeRecord/index'),
        meta: { title: '充值记录', icon: 'nested' }
      },
      {
        path: 'sales',
        name: 'sales',
        component: () => import('@/views/ActivitiesSales/sales/index'),
        meta: { title: '销售统计', icon: 'nested' }
      },
    ]
  },
{
	  path: '/advices',
	  component: Layout,
	  redirect: '/example/table',
	  meta: {
			roles:['admin']
		},
	  children: [
	    {
	      path: 'index',
	      name: 'advices',
	      component: () => import('@/views/advices/index'),
	      meta: { title: '建议反馈', icon: 'password' }
	    },
	  ],

	},


  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
