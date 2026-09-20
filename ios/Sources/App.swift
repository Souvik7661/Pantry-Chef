import SwiftUI
import WebKit

@main
struct PantryChefApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .preferredColorScheme(.dark)
                .ignoresSafeArea()
        }
    }
}

struct ContentView: View {
    var body: some View {
        ZStack {
            Color(red: 0.047, green: 0.051, blue: 0.063).ignoresSafeArea()
            PantryWebView()
                .ignoresSafeArea()
        }
    }
}

struct PantryWebView: UIViewRepresentable {
    func makeUIView(context: Context) -> WKWebView {
        let config = WKWebViewConfiguration()
        config.allowsInlineMediaPlayback = true
        config.mediaTypesRequiringUserActionForPlayback = []
        
        let webView = WKWebView(frame: .zero, configuration: config)
        webView.isOpaque = false
        webView.backgroundColor = UIColor(red: 0.047, green: 0.051, blue: 0.063, alpha: 1.0)
        webView.scrollView.backgroundColor = UIColor(red: 0.047, green: 0.051, blue: 0.063, alpha: 1.0)
        webView.scrollView.contentInsetAdjustmentBehavior = .never
        
        if let htmlPath = Bundle.main.path(forResource: "index", ofType: "html") {
            let fileURL = URL(fileURLWithPath: htmlPath)
            webView.loadFileURL(fileURL, allowingReadAccessTo: fileURL.deletingLastPathComponent())
        } else if let onlineURL = URL(string: "https://pantry-chef-souvik-kundus-projects.vercel.app") {
            webView.load(URLRequest(url: onlineURL))
        }
        
        return webView
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {}
}
