<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    ${name}
    <#list list as student>
        名字:${student.name}<Br>
        性别:${student.sex}<br>

    </#list>
</body>
</html>