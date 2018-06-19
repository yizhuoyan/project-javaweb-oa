;+function(window,document){
	var BLANK="　",DESCENDANT="│",LASTCHILD="└",CHILD="├";
	
	
	
	
	var OptionNode=function(pid,id,content,other){
		this.view=null;
		this.pid=pid;
		this.id=id;
		this.content=content;
		this.other=other;
		
		this.prefix=null;
		this.parent=null;
		this.children;
		this.isLastChild=false;
	};
	
	OptionNode.prototype={
		paint:function(selectEL){
			var optionEL=this.view;
			if(!this.view){
				optionEL=this.view=document.createElement("option");
			}
			optionEL.textContent=this.createPrefix()+this.content;
			for(var key in this.other){
				optionEL[key]=this.other[key];
			}
			selectEL.appendChild(optionEL);
			//paint Children
			var children=this.children;
			if(children){
				for(var i=0;i<children.length;i++){
					children[i].paint(selectEL);
				}
			}
			
		},
		createPrefix:function(){
			var prefix="";
			var parent=this.parent;
			if(parent){
				var parentPrefix=parent.prefix;
				if(parentPrefix){
					prefix+=parentPrefix.slice(0,-1);
				}
				//如果父节点是最后节点
				if(parent.isLastChild){
					prefix+=BLANK;
				}else{
					prefix+=DESCENDANT;
				}
			}
			
			//判断自己是否是最后节点
			if(this.isLastChild){
				prefix+=LASTCHILD;
			}else{
				prefix+=CHILD;
			}
			this.prefix=prefix;
			
			return prefix;
		},
		addChild:function(child){
			if(!this.children){
				this.children=[];
			}else{
				this.children[this.children.length-1].isLastChild=false;	
			}
			child.parent=this;
			child.isLastChild=true;
			this.children.push(child);
		}
	}
	
	
	var TreeSelect=function(selectEL){
		this.selectEL=selectEL;
		this.roots=[];
		this.nodeMap={};
		this.orphansMap={};
		
		TreeSelectInit.apply(this,arguments);
		
	};
	var TreeSelectInit=function(selectEL){
		if(typeof selectEL==="string"){
			this.selectEL=document.querySelector(selectEL);
		}else if(selectEL instanceof window["jQuery"]){
			this.selectEL=selectEL[0];
		}else{
			this.selectEL=selectEL;
		}
	}
	
	TreeSelect.prototype={
		insert:function(pid,id,value,content){
			var node=new OptionNode(pid,id,value,content);
			if(pid===null||typeof pid==="undefined"||pid===""){
				var rootsAmount=this.roots.length;
				if(rootsAmount>0){
					this.roots[rootsAmount-1].isLastChild=false;
				}
				node.isLastChild=true;
				this.roots.push(node);
			}else{
				var parent=this.nodeMap[pid];
				if(parent){
					parent.addChild(node);
				}else{
					//尝试在orphans中找
					this.orphansMap[id]=node;
				}
			}
			this.nodeMap[id]=node;
			return node;
		},
		done:function(){
			var roots=this.roots;
			for(var i=0;i<roots.length;i++){
				roots[i].paint(this.selectEL);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	window.TreeSelect=TreeSelect;
}(window,document);
