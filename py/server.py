import socket, threading

HOST = socket.gethostname()
PORT = 5000

def handle_client(conn, addr):
    print("Nova conexão estabelecida com " + str(addr))

    while True:
        data = conn.recv(1024).decode()

        if not data:
            break

        print("Msg do usuário: " + str(data))
        data = input("Digite sua msg: ")
        conn.send(data.encode())
    conn.close()

def server():
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serverSocket.bind((HOST, PORT))
    serverSocket.listen(5)
    print("Servidor iniciado em " + str(HOST) + ":" + str(PORT))

    while True:
        conn, address = serverSocket.accept()
        print("Conexão para: " + str(address))
        client_thread = threading.Thread(target=handle_client, args=(conn, address))
        client_thread.start()

if __name__ == "__main__":
    server()