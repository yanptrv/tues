from bs4 import BeautifulSoup
import requests as requests

data = requests.get('https://btvnovinite.bg/')
getpage_soup= BeautifulSoup(data.text, 'html.parser')
news = getpage_soup.find(class_ = 'list')

print(news..find(class_ = 'title').text)
print('https://btvnovinite.bg/' + news.find(class_ = 'link')['href'])

