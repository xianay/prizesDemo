# encoding: UTF-8
import re
from sre_parse import Pattern


def d3(number):
    #return re.match("(\d:){2}\d", number)
    #return re.findall(r"\b(?:ab)+\b", number)
    #pattern=re.compile(r"(0\d|1\d|2\d|3[0-3])")
    #p=re.compile(r"(\d:){2}\d")
    #return p.match(number)
    return re.match("([0-9]:){2}[0-9]", number)
    

if d3("1:2:3a"):    
    print "x"