from django.shortcuts import render
from . import models
from django.http import HttpResponseRedirect
from django.contrib import auth
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.contrib.auth.decorators import login_required
from django.contrib import messages
from django.shortcuts import render_to_response
from django.template import RequestContext,Context
# Create your views here.
def massage_page(request):
    informations = models.Massage.objects.all()
    return render(request,'massageboard/edit_board.html',{'informations':informations})
def massage_edit(request):
    if request.user.is_authenticated():
        massage = request.POST.get('massage', 'MASSAGE')
        name = request.POST.get('name', '匿名')
        models.Massage.objects.create(massage=massage, name=name)
        return HttpResponseRedirect("/massageboard/page")
    else:
        messages.error(request,'请先登录再进行留言')
        return HttpResponseRedirect('/massageboard/page')
def massage_login(request):
    return render(request,'massageboard/login.html')
def massage_create(request):
    return render(request,'massageboard/create_account.html')
def create_account(request):
    username=request.POST.get('username')
    password=request.POST.get('password')
    users = User.objects.filter(username=request.POST['username'])
    if users:
        messages.warning(request, '这个账号已经被注册')
        return HttpResponseRedirect('/massageboard/create')
    else:
        user=User.objects.create_user(username=username,password=password)
        user.save()
        return HttpResponseRedirect("/massageboard/page")
def login_account(request):
    username =request.POST.get('username')
    password =request.POST.get('password')
    user=auth.authenticate(username=username,password=password)
    if user:
        auth.login(request,user)
        context_instance = RequestContext(request)
        return HttpResponseRedirect('/massageboard/page')
    else:
        messages.warning(request,'登录名或密码错误')
        return  HttpResponseRedirect('/massageboard/login')
def logout_account(request):
    auth.logout(request)
    return HttpResponseRedirect('/massageboard/page')

