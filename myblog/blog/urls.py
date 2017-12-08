from django.conf.urls import url,include
from . import views
urlpatterns = [
    url(r'^views/',views.index ),
    url(r'^article/(?P<article_id>[0-9]+)$',views.airticle_page ,name='article_page'),
    url(r'^edit/$',views.edit_page,name='edit_page' ),
    url(r'^edit/action',views.edit_action,name='edit_action' )
]
