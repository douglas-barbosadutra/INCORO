import { IncAngPage } from './app.po';

describe('inc-ang App', function() {
  let page: IncAngPage;

  beforeEach(() => {
    page = new IncAngPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
