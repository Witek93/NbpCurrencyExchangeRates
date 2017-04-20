public class Main {
//    public static void main(String[] args) {
//        args = new String[] {"EUR", "2013-01-28", "2015-01-31"};
//
//        QueryRatesRequest queryRatesRequest = new InputDataParser(args).parse();
//        Collection<Year> years = queryRatesRequest.getDateRange().getYears();
//
//        NbpDirectoryServiceAdapter nbpDirectoryServiceAdapter = new NbpDirectoryServiceAdapter(new NbpDirectoryService());
//
//        years.stream()
//                .map(nbpDirectoryServiceAdapter::call)
//                .flatMap(e -> e.getDirectories().stream())
//                .forEach(e -> System.out.println(e));
//    }
}
