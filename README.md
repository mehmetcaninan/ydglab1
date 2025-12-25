Bu proje Jenkins Pipeline içinde bir URL erişilebilirlik kontrolü örneğidir.
Amaç: Jenkins pipeline'da https://example.com adresine erişim kontrolü yapılır; erişilebiliyorsa deploy adımı çalışır, erişilemiyorsa pipeline fail olur.

Eklenen dosyalar:
- `pom.xml` : Maven yapılandırması, JUnit 5 bağımlılıkları.
- `src/test/java/UrlReachabilityTest.java` : Paket bildirimini kullanmayan JUnit testi (Java 11 HttpClient) — https://example.com için 2xx kontrol eder.
- `src/test/java/com/example/UrlReachabilityTest.java` : Aynı testi paketli örnek olarak bıraktım (IDE/CI tercihlerine göre kullanın).
- `Jenkinsfile` : Maven tabanlı pipeline; `mvn test` çalıştırır ve test başarılıysa `Deploy` stage'ine geçer.
- `Jenkinsfile.curl` : Maven gerektirmeyen, doğrudan `curl` ile URL kontrolü yapan alternatif Jenkinsfile (Jenkins üzerinde agent üzerinde curl yüklü olmalıdır).

Yerel çalışma (macOS, zsh):
1) Maven yüklü ise projeyi derleyip testleri çalıştırmak için:

```bash
cd /path/to/ydglabodev1
mvn -B test
```

2) Eğer Maven yoksa veya hızlı bir kontrol istiyorsanız, doğrudan curl ile terminalden kontrol edebilirsiniz:

```bash
curl -I https://example.com
# veya HTTP status kodunu almak için
curl -s -o /dev/null -w '%{http_code}' https://example.com
```

Jenkins kurulumu:
- Eğer Jenkins'te Maven kullanıyorsanız: Yeni pipeline job oluşturun ve repository'nizi gösterin; pipeline script olarak bu repo içindeki `Jenkinsfile` kullanın.
- Eğer Jenkins agent'ınızda curl yüklüyse ve daha hafif bir çözüm istiyorsanız `Jenkinsfile.curl` kullanın.

Notlar ve IDE uyarıları (hangi hataları görebilirsiniz ve nedenleri):
- "Cannot resolve symbol 'junit'" veya benzeri uyarılar IDE'nin Maven bağımlılıklarını indirmemiş olmasından kaynaklanır. `mvn test` çalıştırıldığında veya IDE içinde Maven->Reload/Import işlemi yapıldığında bağımlılıklar çözülecektir.
- "Package name 'com.example' does not correspond to the file path ..." uyarısı, IDE'nin kaynak kökünü farklı algıladığı anlamına gelir. Kaynak kökünüzün `src/test/java` olduğundan emin olun veya paket satırını kaldırıp dosyayı top-level `src/test/java/` içine koyun (projede her iki örnek de eklidir).

Gereksinimler ve durum:
- JUnit testi ile kontrol: Hazır (pom.xml ve test sınıfı eklendi) — Jenkins `mvn test` ile çalıştırılabilir.
- curl ile kontrol: Hazır (`Jenkinsfile.curl`) — agent üzerinde curl gerektirir.

Eğer isterseniz ben Jenkins job tanımlaması için örnek GUI adımlarını veya Branch Pipeline multibranch örneğini ve gerçek bir deploy komutunu (ör: kubectl, scp, ansible) `Deploy` bölümüne nasıl bağlayacağınızı da ekleyebilirim.

