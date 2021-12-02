from django.urls import include, path
from elsys import views

urlpatterns = [
    path('', views.index),
    path('home', views.home),
    path('about', views.about),
    path('cars', views.cars),
    path('longest_comment', views.longest_comment),
    path('post_with_longest_title', views.post_with_longest_title)
]
