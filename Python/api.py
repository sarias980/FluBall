from bottle import route, run
import dbm
import json
import os

db = dbm.open('cache', 'c')
    
@route('/info') # or @route('/login')
def info():	
	params = {}	
	
	params["ssid"] = db.get('ssid', 'fluidra')	
	params["password"] = db.get('pass', '1234')	
	params["name"] = db.get('name', 'Fluchula')	
	
	return json.dumps(params)
        
@route('/setup/<ssid>/<password>/<name>') # or @route('/login', method='POST')
def do_setup(ssid, password, name):
	db['ssid'] = ssid
	db['pass'] = password
	db['name'] = name	
	return info()
	
@route('/wifi') # or @route('/login')
def wifi():		
	execfile("wifi.py")
	return true
	
@route('/juego/<codigo>') # or @route('/login')
def juego(codigo):		
	execfile( codigo + ".py")
	return true
	

run(host='localhost', port=8080, debug=True)
