# -*- coding: utf-8 -*-
import datetime

from django.http import HttpResponse, Http404
from django.shortcuts import render_to_response
from django.template import Context
from django.template.loader import get_template
import MySQLdb

# Welcome页面
def hello(request):
    return HttpResponse('Hello World!')

# 主页
def homePage(request, order):
    # now = datetime.datetime.now()
    db = MySQLdb.connect(user='root', db='rap_db', passwd='123456', host='192.168.0.199', port=3306, charset='utf8')
    cursor = db.cursor()
    sql = ''
    if order == 1 or cmp(order, 1):
        sql = 'SELECT * FROM tb_check_in ORDER BY create_date'
    else :
        sql = 'SELECT * FROM tb_check_in ORDER BY user_id'
    cursor.execute(sql)
    datas = cursor.fetchall()
    # cursor.close()
    # db.close()
    html = '['
    i = 1
    for data in datas :
        html += '{'
        t = data[3]
        usersql = 'SELECT name FROM tb_user WHERE id = ' + repr(int(t))
        cursor.execute(usersql)
        userdata = cursor.fetchall()
        projectsql = 'SELECT name FROM tb_project WHERE id = ' + repr(int(data[4]))
        cursor.execute(projectsql)
        projectdata = cursor.fetchall()
        # cursor.close()
        # db.close()
        str=data[1].strftime("%Y-%m-%d %H:%M:%S")
        if i < len(datas):
            html += '"update_time":"' + str + '",'
            html += '"version":"' + repr(data[6])[2:-1] + '",'
            html += '"project":"' + repr(projectdata[0])[3:-3] + '",'
            html += '"description":"' + repr(data[5])[2:-1] + '",'
            html += '"update_user":"' + repr(userdata[0])[3:-3] + '"' + '},'
            i = i+1
        else :
            html += '"update_time":"' + str + '",'
            html += '"version":"' + repr(data[6])[2:-1] + '",'
            html += '"project":"' + repr(projectdata[0])[3:-3] + '",'
            html += '"description":"' + repr(data[5])[2:-1] + '",'
            html += '"update_user":"' + repr(userdata[0])[3:-3] + '"' + '}'
    html += ']'
    cursor.close()
    db.close()  
    return HttpResponse(html)


# 使用template显示页面
# html文件路径在settings.py中TEMPLATES下的DIRS配置
def current_datetime(request):
    now = datetime.datetime.now()
    t = get_template('current_datetime.html')
    # 使用Context渲染页面
    html = t.render(Context({'current_date' : now}))
    return HttpResponse(html)

# 简化current_datetime
def current_datetime_simple(request):
    current_date = datetime.datetime.now()
    return render_to_response('current_datetime.html', locals())

# 时间+
def hours_ahead(request, offset):
    try:
        offset = int(offset)
    except ValueError:
        raise Http404
    dt = datetime.datetime.now() + datetime.timedelta(hours=offset)
    # assert False
    html = '<html><body>In %s hour(s), it will be %s.</body></html>' % (offset, dt)
    return HttpResponse(html)

# 时间+简化
def hours_ahead_simple(request, hour_offset):
    try:
        hour_offset = int(hour_offset)
    except ValueError:
        raise Http404
    next_time = datetime.datetime.now() + datetime.timedelta(hours=hour_offset)
    return render_to_response('hours_head.html', locals())