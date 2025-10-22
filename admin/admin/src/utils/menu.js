const menu = {
    list() {
        return [
            {
                "backMenu": [
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "首页总数"],
                                "appFrontIcon": "cuIcon-attentionfavor",
                                "buttons": ["新增", "查看", "修改", "删除", "首页总数"],
                                "menu": "用户",
                                "menuJump": "列表",
                                "tableName": "yonghu"
                            }
                        ],
                        "menu": "用户管理"
                    },
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除"],
                                "appFrontIcon": "cuIcon-cardboard",
                                "buttons": ["新增", "查看", "修改", "删除"],
                                "menu": "药品分类",
                                "menuJump": "列表",
                                "tableName": "yaopinfenlei"
                            }
                        ],
                        "menu": "药品分类管理"
                    },
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "药品库存统计", "药品分类统计", "查看评论", "首页总数", "首页统计", "药品入库"],
                                "appFrontIcon": "cuIcon-rank",
                                "buttons": ["新增", "查看", "修改", "删除", "查看评论", "首页总数", "首页统计", "药品入库"],
                                "menu": "药品信息",
                                "menuJump": "列表",
                                "tableName": "yaopinxinxi"
                            }
                        ],
                        "menu": "药品信息管理"
                    },
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除"],
                                "appFrontIcon": "cuIcon-cardboard",
                                "buttons": ["查看", "修改", "删除"],
                                "menu": "入库信息",
                                "menuJump": "列表",
                                "tableName": "rukuxinxi"
                            }
                        ],
                        "menu": "入库信息管理"
                    },
                    {
                        "child": [
                           
                            {
                                "allButtons": ["新增", "查看", "修改", "删除"],
                                "appFrontIcon": "cuIcon-service",
                                "buttons": ["新增", "查看", "修改", "删除"],
                                "menu": "客服中心",
                                "tableName": "chat"
                            },
                            {
                                "allButtons": ["新增", "查看", "修改", "删除"],
                                "appFrontIcon": "cuIcon-similar",
                                "buttons": ["新增", "查看", "修改", "删除"],
                                "menu": "智能助手",
                                "tableName": "chathelper"
                            }
                        ],
                        "menu": "系统管理"
                    },
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "导出", "日销量", "月销量", "年销量", "品销量", "类销量", "日销额", "月销额", "年销额", "品销额", "类销额", "发货", "物流", "核销"],
                                "appFrontIcon": "cuIcon-full",
                                "buttons": ["查看", "发货", "物流"],
                                "menu": "已支付订单",
                                "tableName": "orders/已支付"
                            },
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "导出", "日销量", "月销量", "年销量", "品销量", "类销量", "日销额", "月销额", "年销额", "品销额", "类销额", "物流", "退货审核"],
                                "appFrontIcon": "cuIcon-medal",
                                "buttons": ["查看", "退货审核", "物流", "品销额", "类销额"],
                                "menu": "已完成订单",
                                "tableName": "orders/已完成"
                            },
                 
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "导出", "日销量", "月销量", "年销量", "品销量", "类销量", "日销额", "月销额", "年销额", "品销额", "类销额"],
                                "appFrontIcon": "cuIcon-addressbook",
                                "buttons": ["查看"],
                                "menu": "已退款订单",
                                "tableName": "orders/已退款"
                            },
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "导出", "日销量", "月销量", "年销量", "品销量", "类销量", "日销额", "月销额", "年销额", "品销额", "类销额"],
                                "appFrontIcon": "cuIcon-skin",
                                "buttons": ["查看"],
                                "menu": "已发货订单",
                                "tableName": "orders/已发货"
                            },
                       
                        ],
                        "menu": "订单管理"
                    }
                ],
                "frontMenu": [
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "药品库存统计", "药品分类统计", "查看评论", "首页总数", "首页统计", "药品入库"],
                                "appFrontIcon": "cuIcon-paint",
                                "buttons": ["查看"],
                                "menu": "药品信息列表",
                                "menuJump": "列表",
                                "tableName": "yaopinxinxi"
                            }
                        ],
                        "menu": "药品信息模块"
                    }
                ],
                "hasBackLogin": "是",
                "hasBackRegister": "否",
                "hasFrontLogin": "否",
                "hasFrontRegister": "否",
                "roleName": "管理员",
                "tableName": "users"
            },
            {
                "backMenu": [],
                "frontMenu": [
                    {
                        "child": [
                            {
                                "allButtons": ["新增", "查看", "修改", "删除", "药品库存统计", "药品分类统计", "查看评论", "首页总数", "首页统计", "药品入库"],
                                "appFrontIcon": "cuIcon-paint",
                                "buttons": ["查看"],
                                "menu": "药品信息列表",
                                "menuJump": "列表",
                                "tableName": "yaopinxinxi"
                            }
                        ],
                        "menu": "药品信息模块"
                    }
                ],
                "hasBackLogin": "否",
                "hasBackRegister": "否",
                "hasFrontLogin": "是",
                "hasFrontRegister": "是",
                "roleName": "用户",
                "tableName": "yonghu"
            }
        ];
    }
};

export default menu;
