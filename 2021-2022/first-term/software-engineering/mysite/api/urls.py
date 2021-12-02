from django.urls import include, path
from api import views
from api.views import CarsView
from rest_framework_swagger.views import get_swagger_view
from django.conf.urls import url

schema_view = get_swagger_view(title='Cars API')

urlpatterns = [
    url(r'docs/', schema_view),
    path('cars/get', views.cars_json),
    path('cars/create', views.create_car),
    path('view_cars/', CarsView.as_view()),

]
