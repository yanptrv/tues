from django.shortcuts import render

# Create your views here.

from elsys.models import Car
from api.serializers import CarSerializer
from django.http import JsonResponse

from rest_framework.decorators import authentication_classes, permission_classes
from rest_framework.decorators import api_view
from rest_framework.views import APIView

@api_view(['GET'])
def cars_json(request):
    """
    Returning Cars in json format.
    ---
    response_serializer: CarSerializer
    """
    cars = Car.objects.all()
    serializer = CarSerializer(cars, many = True)
    return JsonResponse({'data': serializer.data}, safe=False)

@api_view(['POST'])
def create_car(request):
    """
    Returning Cars in json format.
    ---
    response_serializer: CarSerializer
    """
    cars = Car.objects.all()
    serializer = CarSerializer(cars, many = True)
    return JsonResponse({'data': serializer.data }, safe=False)

class CarsView(APIView):
    def get(self, request):
        cars = Car.objects.all()
        serializer = CarSerializer(cars, many = True)
        return JsonResponse({'data': serializer.data}, safe=False)

    def post(self, request):
        serializer = CarSerializer(data = request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, safe=False, status=201)
        return JsonResponse(serializer.errors, safe=False, status=400)

    def delete(self, requests):
        pass
