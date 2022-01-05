"""Django imports"""
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from elsys.processors.api_processor import ApiProcessor

# Create your views here.
PI = 3.14


def index():
    """Hello world text"""
    return HttpResponse("Hello, world. You're at the polls index.")


def home(request):
    """The home page"""
    return render(request, 'home.html')


def about(request):
    """About the page"""
    return render(request, 'about.html')


def cars(request):
    """All cars"""
    return render(request, 'cars.html')


def longest_comment():
    """Longest comment"""
    return JsonResponse(ApiProcessor.longest_comment(), safe=False)


def post_with_longest_title():
    """Longest tittle"""
    return JsonResponse(ApiProcessor.post_with_longest_title(), safe=False)
