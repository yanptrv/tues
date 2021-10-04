from django.conf import settings
from django.conf.urls.static import static
from django.urls import path

from . import views

app_name = 'hello_world'

urlpatterns = [
    path('', views.main, name='main'),
    path('home/', views.home_page, name='home')
]