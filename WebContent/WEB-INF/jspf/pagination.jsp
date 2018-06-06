<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="p" value="${requestScope[param.resultVarName]}" scope="page"></c:set>

<footer class="box-footer">
				<div class="row">
					<div class="col-xs-6">
						<span> 共<b>${p.totalRows}</b>条
						</span> <span> 每页<b>${p.pageSize}</b>条
						</span> <span> 共<b>${p.totalPages}</b>页
						</span>
					</div>

					<div class="col-xs-6" style="text-align: right">
						<c:if test="${p.pageNo!=1}">
							<a href="javascript:_(1)">首页</a>
							<a href="javascript:_(${p.pageNo-1})">上一页</a>
						</c:if>
						<c:if test="${p.pageNo==1}">
							<span>首页</span>
							<span>上一页</span>
						</c:if>
						<b>${p.pageNo}</b>
						<c:if test="${p.pageNo!=p.totalPages}">
							<a href="javascript:_(${p.pageNo+1})">下一页</a>
							<a href="javascript:_(${p.totalPages})">末页</a>
						</c:if>
						<c:if test="${p.pageNo==p.totalPages}">
							<span>下一页</span>
							<span>末页</span>
						</c:if>
					</div>
				</div>
				<script>
					function _(no){
						${param.pageClickHandler}(no);
					}
				</script>
			</footer>
			