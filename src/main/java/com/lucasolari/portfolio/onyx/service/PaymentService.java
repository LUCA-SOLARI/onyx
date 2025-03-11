package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.exceptions.product.ProductNotExistentException;
import com.lucasolari.portfolio.onyx.util.MonetaryConverter;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PaymentService {

    private GuestOrderService guestOrderService;
    private String DOMAIN;

    @Autowired
    public PaymentService(GuestOrderService guestOrderService, @Value("${stripe.domain}") String DOMAIN) {
        this.guestOrderService = guestOrderService;
        this.DOMAIN=DOMAIN;
    }

    public String createStripeCheckoutSession(Cart cart) throws StripeException, ProductNotExistentException {

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(DOMAIN + "/payment/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(DOMAIN + "/payment/cancel")
                .setPaymentMethodConfiguration("pmc_1Qw0JoLzjOQDqIWKDLIRrjTu")
                .setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder()
                        .addAllAllowedCountry(getStripeCompleteAllowedCountries())
                        .build())
                .setCustomerCreation(SessionCreateParams.CustomerCreation.ALWAYS)
                .addShippingOption(SessionCreateParams.ShippingOption.builder()
                        .setShippingRate("shr_1Qx4sPLzjOQDqIWKf30gLZlr")
                        .build())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity((long)cart.getQty())
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(MonetaryConverter.convertBigDecimalToCents(cart.getProduct().getPrice()))
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(cart.getProduct().getName())
                                                                .build())
                                                .build())
                                .build())
                .build();

        Session stripeCheckoutSession= Session.create(params);
        guestOrderService.createPendingGuestOrder(cart,stripeCheckoutSession.getId());
        return stripeCheckoutSession.getUrl();
    }

    public Session retrieveStripeCheckoutSession(String stripeCheckoutSessionId)throws StripeException{

       return Session.retrieve(stripeCheckoutSessionId);
    }

    private List<SessionCreateParams.ShippingAddressCollection.AllowedCountry> getStripeCompleteAllowedCountries(){

        List<SessionCreateParams.ShippingAddressCollection.AllowedCountry> completeAllowedCountries =
                new ArrayList<SessionCreateParams.ShippingAddressCollection.AllowedCountry>(Arrays.asList(
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AQ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AX,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.AZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BB,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BJ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BQ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.BZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.CZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DJ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.DZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.EC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.EE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.EG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.EH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ER,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ES,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ET,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.FI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.FJ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.FK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.FO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.FR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GB,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GP,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GQ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.GY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.HK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.HN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.HR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.HT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.HU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ID,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IQ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.IT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.JE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.JM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.JO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.JP,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.KZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LB,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.LY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ME,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ML,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MQ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MX,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.MZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NP,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.NZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.OM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.PY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.QA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.RE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.RO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.RS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.RU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.RW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SB,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SI,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SJ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ST,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SX,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.SZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TD,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TH,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TJ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TL,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TO,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TR,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TV,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.TZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.UA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.UG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.US,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.UY,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.UZ,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VC,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VG,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VN,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VU,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.WF,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.WS,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.XK,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.YE,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.YT,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ZA,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ZM,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ZW,
                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.ZZ));

        return completeAllowedCountries;

    }
}
