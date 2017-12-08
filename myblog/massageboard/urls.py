from django.conf.urls import url,include
from . import views
urlpatterns = [
    url(r'^page$',views.massage_page ,name='page'),
    url(r'^edit$',views.massage_edit,name='edit'),
    url(r'^login$',views.massage_login,name='login'),
    url(r'^create$',views.massage_create,name='create'),
    url(r'^account$', views.create_account, name='account'),
    url(r'^login_account$',views.login_account,name='login_account'),
    url(r'^logout_account$',views.logout_account,name='logout_account')
]
