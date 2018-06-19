;+function(window,document){
    window.loadModuleTableTree = function(container,opts) {
       return new Promise(function(ok,fail){
        $(container).fancytree($.extend({
            source : {
                url : "api/system/module/list",
                cache : true
            },
            postProcess : function(evt, data) {
                var resp = data.response;
                if (resp.code === "ok") {
                    data.result = covertFlatList2Tree(resp.data);
                } else {
                    alert(resp.message);
                }
            },
            icon : false,
            checkbox : true,
            selectMode : 3,
            minExpandLevel : 1,
            extensions : [ "table" ],
            table : {
                nodeColumnIdx : 1,
                indentation : 16
            },
            strings : {
                loading : "加载中...请稍后",
                loadError : "加载数据异常，请联系管理员",
                moreData : "More...",
                noData : "无功能数据"
            },
            init : function(evt, ctx, flag) {
                tree = ctx.tree;
                ok();
            },
            activate : function(evt, ctx) {
                var node = ctx.node;
                node.setSelected(true);
            },
            deactivate : function(evt, ctx) {
                var node = ctx.node;
                node.setSelected(false);
            },
            select : function(evt, ctx) {
                var node = ctx.node;
            }
        },opts||{}));
       });
    };
    var covertFlatList2Tree = function(list) {
        //保存所有node的map
        var nodeMap = {};
        //保存所有根节点
        var roots = [];
        list.filter(function(item) {
            item.key = item.id;
            item.title = "(" + item.code + ")" + item.name;
            nodeMap[item.id] = item;
            if (item.parent) {
                return true;
            } else {
                roots.push(item);
                return false;
            }
        }).forEach(function(item) {
            var parent = nodeMap[item.parent.id];
            if (parent.children) {
                parent.children.push(item);
            } else {
                parent.children = [ item ];
            }
        });
        return roots;
    };

    
}(window,document);