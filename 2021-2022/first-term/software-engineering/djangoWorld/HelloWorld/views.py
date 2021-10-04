from django.shortcuts import render, redirect

def main(request):
    return redirect('home/')

def home_page(request):
    return render(request, 'home.html')

# Create your views here.
