# 🧠 Java Socket-Based Chat Application

A simple real-time **Client-Server Chat System** built using pure **Java Socket Programming** — with GUI support, threading, and core network fundamentals implemented from scratch.

This project was developed during my 4th semester to deepen my understanding of how real-time communication works over TCP/IP.

I have hidden Port number and IP Address with xxxxx.. So, Use IP Address of your Internet in xxx.xxx.xxx.xxx and use any port from 1024 to 9000 in the xxxx value. Remember to maintain the same internet connection when using both Java Programs

---

## 📽️ Demo

> Watch the live demo below to see the project in action:

[▶️ Click to Watch Demo](Demo.mp4)

Or open the `Demo.mp4` file directly from this repo if you're browsing locally.

---

## 🚀 Features

- 🔌 **Socket Programming in Java**
- 🪟 **GUI-based client interface** using Swing
- 🔄 **Bi-directional real-time messaging**
- 🧵 **Multithreading** for simultaneous read/write streams
- 🧪 Works over local network (`localhost` or LAN)

---

## 🧱 Architecture

```txt
  +---------+        TCP/IP         +----------+
  | Client  |  <----------------->  |  Server  |
  +---------+                      +----------+

  - Client has a GUI for typing & viewing messages
  - Server handles incoming messages in a separate thread
