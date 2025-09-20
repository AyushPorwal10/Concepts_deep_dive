The reason for this limit is that when you send an Intent to start another component (especially one in a different process), the data is packaged into a Bundle and sent through Android's Binder framework.

What is Binder? 
The Binder is the core mechanism for Inter-Process Communication (IPC) on Android. It has a limited, fixed-size memory buffer (the transaction buffer) for handling all ongoing transactions for an application process.

The 1 MB Limit: This buffer is typically around 1 MB in size. It's important to know that this buffer is shared by all active transactions in your app's process, not just for a single Intent.
So if multiple things are happening at once, the available space for your Intent could be even less.

The Crash:
If we try to send data that exceeds this buffer size, our application will crash with a TransactionTooLargeException. 
This is a clear signal that we are trying to send too much data."
