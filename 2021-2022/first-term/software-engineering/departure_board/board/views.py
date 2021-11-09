import requests
from django.shortcuts import render


def index(request):
    api = requests.get('https://api-v3.mbta.com/predictions?page%5Boffset%5D=0&page%5Blimit%5D=10&sort=departure_time'
                       '&include=schedule%2Ctrip&filter%5Bdirection_id%5D=0&filter%5Bstop%5D=place-north')

    json_data = api.json()

    for train in json_data['data']:
        train_schedule_id = train['relationships']['schedule']['data']['id']
        train_trip_id = train['relationships']['trip']['data']['id']
        for info_train in json_data['included']:
            if train_schedule_id == info_train['id']:
                departure_time = info_train['attributes']['departure_time']
                train['attributes']['departure_time'] = departure_time[11:16]
            if train_trip_id == info_train['id']:
                train['attributes']['name'] = info_train['attributes']['name']
                train['attributes']['headsign'] = info_train['attributes']['headsign']

    # print('TIME - DESTINATION - TRAIN# - STATUS', end='\n \n')
    # for x in range(0, 10):
    #     print(json_data['data'][x]['attributes']['departure_time'], end=" - ")
    #     print(json_data['included'][x]['attributes']['headsign'], end=" - ")
    #     print(json_data['included'][x]['attributes']['name'], end=" - ")
    #     print(json_data['data'][x]['attributes']['status'])

    return render(request, 'home.html', {'trains': json_data['data']})
