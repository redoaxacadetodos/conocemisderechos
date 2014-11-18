package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(SentidoController)
@Mock(Sentido)
class SentidoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/sentido/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.sentidoInstanceList.size() == 0
        assert model.sentidoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.sentidoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.sentidoInstance != null
        assert view == '/sentido/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/sentido/show/1'
        assert controller.flash.message != null
        assert Sentido.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/sentido/list'

        populateValidParams(params)
        def sentido = new Sentido(params)

        assert sentido.save() != null

        params.id = sentido.id

        def model = controller.show()

        assert model.sentidoInstance == sentido
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/sentido/list'

        populateValidParams(params)
        def sentido = new Sentido(params)

        assert sentido.save() != null

        params.id = sentido.id

        def model = controller.edit()

        assert model.sentidoInstance == sentido
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/sentido/list'

        response.reset()

        populateValidParams(params)
        def sentido = new Sentido(params)

        assert sentido.save() != null

        // test invalid parameters in update
        params.id = sentido.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/sentido/edit"
        assert model.sentidoInstance != null

        sentido.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/sentido/show/$sentido.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        sentido.clearErrors()

        populateValidParams(params)
        params.id = sentido.id
        params.version = -1
        controller.update()

        assert view == "/sentido/edit"
        assert model.sentidoInstance != null
        assert model.sentidoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/sentido/list'

        response.reset()

        populateValidParams(params)
        def sentido = new Sentido(params)

        assert sentido.save() != null
        assert Sentido.count() == 1

        params.id = sentido.id

        controller.delete()

        assert Sentido.count() == 0
        assert Sentido.get(sentido.id) == null
        assert response.redirectedUrl == '/sentido/list'
    }
}
