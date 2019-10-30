import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import Table from '../components/Table'
import Form from '../components/Form'
import Login from '../components/Login'
import BarCharts from '../components/BarCharts'
import PieCharts from '../components/PieCharts'
import Update from '../components/Update'
import Test from '@/components/Test'

Vue.use(Router)

const router = new Router({

    mode: 'history',
    // routes:[
    //     {path: '/login', component: Login, name: '登录'},
    //     {
    //         path: '/',
    //         name: '图书管理',
    //         component: Test,
    //         iconCls: 'el-icon-message',
    //         redirect: '/table',
    //         menuShow: true,
    //         children: [
    //             {path: '/table', component: Table, name: '查询图书',menuShow: true},
    //             {path: '/form', component: Form, name: '添加图书',menuShow: true}
    //         ]
    //     },
    //     {
    //         path: '/charts',
    //         name: '数据统计',
    //         component: Test,
    //         iconCls: 'el-icon-setting',
    //         menuShow: true,
    //         children: [
    //             {path: '/piecharts', component: PieCharts, name: '饼图',menuShow: true},
    //             {path: '/barcharts', component: BarCharts, name: '柱状图',menuShow: true}
    //         ]
    //     },
    //     {
    //         path: '/update',
    //         name: '修改图书',
    //         component: Test,
    //         redirect: '/edit',
    //         children: [
    //             {path: '/edit', component: Update, name: '修改图书'}
    //         ]
    //     }
    // ]


    routes:[
        {
            path: '/',
            name: '图书管理',
            component: Home,
            // leaf: true, // 只有一个节点
            menuShow: true,
            redirect: '/table',
            iconCls: 'iconfont icon-home', // 图标样式class
            children: [
                {path: '/table', component: Table, name: '查询图书', menuShow: true},
                {path: '/form', component: Form, name: '添加图书', menuShow: true}
            ]
        },
        {
            path: '/charts',
            name: '数据统计',
            component: Home,
            menuShow: true,
            redirect: '/barcharts',
            iconCls: 'iconfont icon-setting1',
            children: [
                {path: '/barcharts', component: BarCharts, name: '柱状图', menuShow: true},
                {path: '/piecharts', component: PieCharts, name: '饼图', menuShow: true}
            ]
        },
        {
            path: '/login',
            name: '登录',
            component: Login
        },
        {
            path: '/test',
            name: '登录',
            component: () => import(/* webpackChunkName: "about" */ '../components/Test.vue')
        },
        {
            path: '/update',
            name: '修改图书',
            component: Home,
            redirect: '/edit',
            iconCls: 'iconfont icon-home', // 图标样式class
            children: [
                {path: '/edit', component: Update, name: '修改图书'}
            ]
        }
        ]
})

router.beforeEach((to, from, next) => {
    // console.log('to:' + to.path)
    if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('access-user')
    next()
} else {
    let user = JSON.parse(window.localStorage.getItem('access-user'))
    if (!user) {
        next({path: '/login'})
    } else {
        next()
    }
}
})

export default router