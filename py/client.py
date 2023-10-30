import socket

def client():
    host = socket.gethostname()
    port = 5000
    
    client_socket = socket.socket()
    client_socket.connect((host, port))
    
    print("Cliente conectado em " + str(host) + ":" + str(port))
    message = input("Digite sua msg: ")

    while message.lower().strip() != "bye":
        client_socket.send(message.encode())
        data = client_socket.recv(1024).decode()
        print("Msg do servidor: " + data)
        message = input("Digite sua msg: ")

    client_socket.close()


if __name__ == "__main__":
    client()