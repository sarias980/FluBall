import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(17, GPIO.OUT) 
GPIO.setup(27, GPIO.OUT)

def blink():
        print "Ejecucion iniciada..."
        iteracion = 0
        while iteracion < 30: 
                GPIO.output(17, True) 
                GPIO.output(27, False) 
                time.sleep(1) 
                GPIO.output(17, False) 
                GPIO.output(27, True) 
                time.sleep(1) 
                iteracion = iteracion + 2 
        print "Ejecucion finalizada"
        GPIO.cleanup() 

blink()