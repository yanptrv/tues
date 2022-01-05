"""Importing necessary stuff"""
from django.http import JsonResponse
from rest_framework.decorators import api_view
from rest_framework.views import APIView
from elsys.models import Car
from api.serializers import CarSerializer


@api_view(['GET'])
def cars_json():
    """
    Returning Cars in json format.
    ---
    response_serializer: CarSerializer
    """
    cars = Car.objects.all()
    serializer = CarSerializer(cars, many=True)
    return JsonResponse({'data': serializer.data}, safe=False)


@api_view(['POST'])
def create_car():
    """
    Returning Cars in json format.
    ---
    response_serializer: CarSerializer
    """
    cars = Car.objects.all()
    serializer = CarSerializer(cars, many=True)
    return JsonResponse({'data': serializer.data}, safe=False)


class CarsView(APIView):
    """Car class"""
    @staticmethod
    def get():
        """Getter"""
        cars = Car.objects.all()
        serializer = CarSerializer(cars, many=True)
        return JsonResponse({'data': serializer.data}, safe=False)

    @staticmethod
    def post(request):
        """Posting"""
        serializer = CarSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, safe=False, status=201)
        return JsonResponse(serializer.errors, safe=False, status=400)

    def delete(self, requests):
        """Deleting"""
