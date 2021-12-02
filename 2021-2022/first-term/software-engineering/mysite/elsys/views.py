from django.shortcuts import render
from elsys.processors.api_processor import ApiProcessor
# Create your views here.
PI = 3.14

from django.http import HttpResponse, JsonResponse
from rest_framework.response import Response
def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")

def home(request):
    return render(request, 'home.html')

def about(request):
    return render(request, 'about.html')

def cars(request):
    return render(request, 'cars.html')

def longest_comment(request):
    return JsonResponse(ApiProcessor.longest_comment(), safe=False)

def post_with_longest_title(request):
    return JsonResponse(ApiProcessor.post_with_longest_title(), safe=False)
