<title>${html_title}</title>
<a href="customer_add.html">customer add</a> <br>
<#list customers as customer>
    <b>customer</b> id: <a href="customer_find.json?param_id=${customer.id}">find ${customer.id}</a> <a
        href="?param_id=${customer.id}">delete</a> <a href="customer_update.html?param_id=${customer.id}">update</a>
    <br>
    <b>customer</b> name: ${customer.name} <br>
    <b>customer</b> age: ${customer.age} <br>
    <hr>
</#list>