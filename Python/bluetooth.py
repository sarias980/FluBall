impoort time
import bluetooth
from wakeonlan import

phone = "xx:xx:xx:xx:xx:xx"

def search():
	device = bluetooth.discover_devices(duration = 5, lookup_names = True)
	return devices

while Treu: 
	results = search()

	for addr, name in results	
		if addr == phone:
			wol.send_magic_packet('xx:xx:xx:xx:xx:xx')

		time.sleep(20)