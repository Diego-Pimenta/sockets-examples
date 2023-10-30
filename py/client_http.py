import socket, sys

if len(sys.argv) != 3 and len(sys.argv) != 2:
    print("Use: ", sys.argv[0], " hostname [port]")
    exit(1)

hostname = sys.argv[1]
port = 80

if len(sys.argv) == 3:
    port = int(sys.argv[2])

READBUFF = 16384
s = None

print("Conectando ao host e port:", hostname, port)

for res in socket.getaddrinfo(hostname, port, socket.AF_INET, socket.SOCK_STREAM):
    af, socktype, proto, canonname, sa = res

    try:
        s = socket.socket(af, socktype, proto)
    except socket.error:
        s = None
        continue

    try:
        print("Tentando " + sa[0])
        s.connect(sa)
    except socket.error as msg:
        print("Error:", msg)
        s.close()
        s = None
        continue

    if s:
        print("Solicitando página para " + sa[0])
        s.send(("GET / HTTP/1.1\r\nHost:" + hostname + "\r\n\r\n").encode())
        finished = False
        count = 0
        
        while not finished:
            data = s.recv(READBUFF)
            count += 1

            if len(data) != 0:
                print(repr(data))
            else:
                finished = False

        s.shutdown(socket.SHUT_WR)
        s.close()
        print("Dados recebidos: " + str(count))
        break