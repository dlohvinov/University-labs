import re

        # #WFLYCTL ERROR

source_logs = open("server.log.2018-04-04", "r")
logs = source_logs.readlines()
for line in logs:
        pattern = '.*WFLY'
        match = re.match(pattern, line)
        if match:
            print(line)

source_logs.close()
